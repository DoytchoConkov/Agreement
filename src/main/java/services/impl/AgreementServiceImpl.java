package services.impl;

import classes.Agreement;
import classes.BaseClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import services.AgreementService;

import java.io.*;

public class AgreementServiceImpl implements AgreementService {

    @Override
    public String saveAgreement(Agreement agreement) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        String path = "src/main/java/agreementFiles/" + agreement.getName().replaceAll("/", "-") + ".json";

        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(agreement, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public Agreement getAgreement(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));
        Agreement agreement = gson.fromJson(reader, Agreement.class);
        setParent(agreement);
        return agreement;
    }

    private void setParent(BaseClass parent) {
        parent.getProducts().forEach(pr -> {
            pr.setParentObject(parent);
            if (!pr.getProducts().isEmpty()) {
                setParent(pr);
            }
        });
    }
}
