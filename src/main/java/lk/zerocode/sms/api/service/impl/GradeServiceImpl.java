package lk.zerocode.sms.api.service.impl;

import lk.zerocode.sms.api.controller.request.GradeRequest;
import lk.zerocode.sms.api.controller.response.GradeSummaryResponse;
import lk.zerocode.sms.api.exception.GradeNotFoundException;
import lk.zerocode.sms.api.exception.TeacherNotFoundException;
import lk.zerocode.sms.api.model.Gender;
import lk.zerocode.sms.api.model.Grade;
import lk.zerocode.sms.api.model.Teacher;
import lk.zerocode.sms.api.projection.GradeSummaryProjection;
import lk.zerocode.sms.api.repository.GradeRepository;
import lk.zerocode.sms.api.repository.StudentRepository;
import lk.zerocode.sms.api.repository.TeacherRepository;
import lk.zerocode.sms.api.service.GradeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Grade creteGrade(Long teacherId, GradeRequest gradeRequest) throws TeacherNotFoundException {

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new TeacherNotFoundException(teacherId + ": Teacher not found"));

        Grade grade = modelMapper.map(gradeRequest, Grade.class);
        grade.setTeacher(teacher);
        gradeRepository.save(grade);

        return modelMapper.map(grade, Grade.class);
    }

    @Override
    public List<Grade> readAllGrades() {
        return gradeRepository.findAll();
    }


    @Override
    public Grade getGradeById(Long gradeId) throws GradeNotFoundException {

        Grade grade = gradeRepository.findById(gradeId).orElseThrow(() -> new GradeNotFoundException(gradeId + ": Grade not found"));

        Teacher teacher = grade.getTeacher();
        Grade mappedGrade = modelMapper.map(grade, Grade.class);
        mappedGrade.setTeacher(teacher);

        return mappedGrade;
    }

    @Override
    public Grade updateById(Long gradeId, GradeRequest gradeRequest) throws GradeNotFoundException {

        Grade grade = gradeRepository.findById(gradeId).orElseThrow(() -> new GradeNotFoundException(gradeId + ": Grade not found"));

        Teacher teacher = new Teacher();
        teacher.setId(gradeRequest.getTeacherId());

        Grade mappedGrade = modelMapper.map(gradeRequest, Grade.class);
        mappedGrade.setTeacher(teacher);
        mappedGrade.setId(grade.getId());

        gradeRepository.save(mappedGrade);

        return mappedGrade;
    }

    @Override
    public List<GradeSummaryResponse> getGradeTeacherDetails() {
        List<GradeSummaryProjection> projections = studentRepository.findGradeSummaries(Gender.FEMALE, Gender.MALE);
        return projections.stream().map(projection -> new GradeSummaryResponse(projection.getGrade(), projection.getGradeName(), projection.getTeacherName(), projection.getFemaleCount(), projection.getMaleCount())).toList();
    }
}
