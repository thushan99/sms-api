package lk.zerocode.School_management_system.service.impl;

import lk.zerocode.School_management_system.controller.request.GradeRequest;
import lk.zerocode.School_management_system.controller.response.GradeSummaryResponse;
import lk.zerocode.School_management_system.exception.GradeNotFoundException;
import lk.zerocode.School_management_system.exception.TeacherNotFoundException;
import lk.zerocode.School_management_system.model.Gender;
import lk.zerocode.School_management_system.model.Grade;
import lk.zerocode.School_management_system.model.Teacher;
import lk.zerocode.School_management_system.projection.GradeSummaryProjection;
import lk.zerocode.School_management_system.repository.GradeRepository;
import lk.zerocode.School_management_system.repository.StudentRepository;
import lk.zerocode.School_management_system.repository.TeacherRepository;
import lk.zerocode.School_management_system.service.GradeService;
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
