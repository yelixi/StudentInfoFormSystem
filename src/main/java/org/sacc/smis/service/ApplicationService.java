package org.sacc.smis.service;

import org.sacc.smis.entity.Application;

import java.util.List;

public interface ApplicationService {
    boolean addApplication(Application application);

    boolean deleteApplication(Integer applicationId);

    boolean updateApplication(Application application);

    List<Application> findAllApplications();

    Application getApplicationById(Integer applicationId);

}
