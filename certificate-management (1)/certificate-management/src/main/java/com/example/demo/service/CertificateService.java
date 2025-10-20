package com.example.demo.service;

import com.example.demo.entity.Certificate;
import java.util.List;
import java.util.Optional;

public interface CertificateService {
    List<Certificate> getAllCertificates();
    Certificate createCertificate(Certificate certificate);
    Optional<Certificate> getCertificateById(Long id);
    Certificate updateCertificate(Long id, Certificate certificate);
    void deleteCertificate(Long id);
}
