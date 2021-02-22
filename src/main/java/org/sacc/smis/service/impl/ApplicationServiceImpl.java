package org.sacc.smis.service.impl;

import org.sacc.smis.entity.Application;
import org.sacc.smis.mapper.ApplicationRepository;
import org.sacc.smis.service.ApplicationService;
import org.sacc.smis.util.GetNullPropertyNamesUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;


    @Override
    public boolean addApplication(Application application) {
        applicationRepository.save(application);
        return true;
    }

    @Override
    public boolean deleteApplication(Integer applicationId) {
        applicationRepository.deleteById(applicationId);
        return true;
    }

    @Override
    public boolean updateApplication(Application application) {
        Application a = applicationRepository.findByPrimaryKey(application.getId());
        BeanUtils.copyProperties(application, a, GetNullPropertyNamesUtil.getNullPropertyNames(application));
        applicationRepository.save(a);
        return true;
    }

    @Override
    public List<Application> findAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public Application getApplicationById(Integer applicationId) {
        return applicationRepository.getOne(applicationId);
    }
}
