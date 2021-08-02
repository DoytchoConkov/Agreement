package services;

import classes.Agreement;

import java.io.FileNotFoundException;

public interface AgreementService {
    String saveAgreement(Agreement agreement);

    Agreement getAgreement(String path) throws FileNotFoundException;
}
