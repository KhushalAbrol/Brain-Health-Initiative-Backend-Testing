package com.healthcare.service;

import com.healthcare.dao.ConsultationFormDao;
import com.healthcare.dao.DoctorDao;
import com.healthcare.dao.HospitalDao;
import com.healthcare.dao.PatientDao;
import com.healthcare.entity.ConsultationForm;
import com.healthcare.entity.Doctor;
import com.healthcare.entity.Hospital;
import com.healthcare.entity.Patient;
import com.healthcare.exception.APIRequestException;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultationFormService {

    @Autowired
    private ConsultationFormDao consultationFormDao;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private HospitalDao hospitalDao;


    public ConsultationForm createConsultationForm(ConsultationForm consultationForm){
        Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        if(consultationForm.getIcdDescription() == ""){
            throw new APIRequestException("ICD Description Cannot be Empty!");
        }
        if(consultationForm.getCompliant()== ""){
            throw new APIRequestException("Compliant Cannot be Empty!");
        }
        if(!DateUtils.isSameDay(consultationForm.getDateAndTime(), today)){
            throw new APIRequestException("Invalid Date ! Consultation form can be created for present date");
        }
        Doctor doctor = doctorDao.findByHealthId(consultationForm.getDoctor().getDoctor().getUserId());
        if(doctor==null) throw new APIRequestException("Invalid doctor id");
        Optional<Hospital> hospital = hospitalDao.findById(consultationForm.getHospital().getHospitalId());
        if(!hospital.isPresent()) throw new APIRequestException("Invalid hospital");
        Patient patient = patientDao.findByAbhaId(consultationForm.getPatient().getAbhaId());
        if(patient==null) throw new APIRequestException("Invalid patient");
        Doctor refer = null;
        if(consultationForm.getRefer()!=null) refer = doctorDao.findByHealthId((consultationForm.getRefer().getDoctor().getUserId()));
        consultationForm.setDoctor(doctor);
        consultationForm.setRefer(refer);
        consultationForm.setHospital(hospital.get());
        consultationForm.setPatient(patient);
        return consultationFormDao.save(consultationForm);
    }

    public List<ConsultationForm> getAllConsultationForm(){
        List<ConsultationForm>  consultationForm = (List<ConsultationForm>) consultationFormDao.findAll();
        return consultationForm;
    }

    public List<ConsultationForm> getConsultationFormByAbhaId(String id){

        return consultationFormDao.findByAbhaId(id);
    }

    public List<ConsultationForm> getConsultationFormByRefer(Long id){
        return consultationFormDao.findByRefer(id);
    }

    public List<ConsultationForm> getConsultationFormByDoctor(Long id){
        return consultationFormDao.findByDoctor(id);
    }

    public List<ConsultationForm> getFormsWithQuestionnaire(){
        return consultationFormDao.formsWithQuestionnaire();
    }

}
