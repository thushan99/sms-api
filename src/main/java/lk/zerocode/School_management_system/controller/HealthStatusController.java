package lk.zerocode.School_management_system.controller;

import lk.zerocode.School_management_system.controller.request.HealthStatusRequest;
import lk.zerocode.School_management_system.controller.response.HealthStatusResponse;
import lk.zerocode.School_management_system.controller.response.StudentPreviousSchoolResponse;
import lk.zerocode.School_management_system.exception.HealthStatusNotFoundException;
import lk.zerocode.School_management_system.exception.StudentInactiveException;
import lk.zerocode.School_management_system.exception.StudentNotFoundException;
import lk.zerocode.School_management_system.model.StudentHealth;
import lk.zerocode.School_management_system.service.StudentHealthService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class HealthStatusController {

    private StudentHealthService studentHealthService;
    private ModelMapper modelMapper;

    @PostMapping(value = "/students/{student-id}/health-status", headers = "X-Api-Version=v1")
    public ResponseEntity<HealthStatusResponse> create(@PathVariable("student-id") Long studentId,
                                                       @RequestBody HealthStatusRequest healthStatusRequest) {
        try {
            StudentHealth studentHealth = studentHealthService.create(studentId, healthStatusRequest);
            HealthStatusResponse statusResponse = modelMapper.map(studentHealth, HealthStatusResponse.class);
            return new ResponseEntity<>(statusResponse, HttpStatus.CREATED);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/students/{student-id}/health-status", headers = "X-Api-Version=v1")
    public ResponseEntity<List<HealthStatusResponse>> getAll(@PathVariable("student-id") Long studentId) {

        try {
            List<StudentHealth> studentHealths = studentHealthService.getAll(studentId);
            List<HealthStatusResponse> healthStatusResponses = studentHealths.stream()
                    .map(studentHealth -> modelMapper.map(studentHealth, HealthStatusResponse.class)).toList();
            return new ResponseEntity<>(healthStatusResponses, HttpStatus.OK);
        } catch ( StudentInactiveException | HealthStatusNotFoundException |StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/students/{student-id}/health-status/{health-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<HealthStatusResponse> getById(@PathVariable("student-id") Long studentId,
                                                        @PathVariable("health-id") Long healthId) {
        try {
            StudentHealth studentHealth = studentHealthService.getById(studentId, healthId);
            HealthStatusResponse healthStatusResponse = modelMapper.map(studentHealth, HealthStatusResponse.class);
            return new ResponseEntity<>(healthStatusResponse, HttpStatus.OK);
        } catch (StudentInactiveException | HealthStatusNotFoundException | StudentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/students/{student-id}/health-status/{health-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<HealthStatusResponse> updateById(@PathVariable("student-id") Long studentId,
                                                           @PathVariable("health-id") Long healthId,
                                                           @RequestBody HealthStatusRequest healthStatusRequest) throws StudentNotFoundException {
        try {
            StudentHealth heathStatus = studentHealthService.updateById(studentId, healthId, healthStatusRequest);
            HealthStatusResponse healthStatusResponse = modelMapper.map(heathStatus, HealthStatusResponse.class);
            return new ResponseEntity<>(healthStatusResponse, HttpStatus.OK);
        } catch (StudentInactiveException | HealthStatusNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/students/{student-id}/health-status/{health-id}", headers = "X-Api-Version=v1")
    public ResponseEntity<StudentPreviousSchoolResponse> deleteById(@PathVariable("student-id") Long studentId,
                                                                    @PathVariable("health-id") Long healthId) {
        try {
            studentHealthService.deleteById(studentId, healthId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (StudentNotFoundException | StudentInactiveException |
                 HealthStatusNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
