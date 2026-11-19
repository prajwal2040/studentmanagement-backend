package com.example.studentmanagement.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import com.google.genai.types.Schema;
import com.google.genai.types.Type;

@Service
public class GeminiCertificateService {

    public String extractCertificateDetails(Path imagePath)
            throws IOException {

        byte[] imageData = Files.readAllBytes(imagePath);

        Content content = Content.fromParts(

                Part.fromText("""
                        Analyze this certificate.

                        Extract ONLY these four details:

                        1. Certificate Name
                        2. Issuing Organization
                        3. Course Duration
                        4. Issue Date

                        Rules:
                        - Do not guess any information.
                        - If a field is not visible or not mentioned, return "N/A".
                        -For Issue Date ,return the date in YYYY-MM-DD format.
                        - For Issue Date is not visible, return "N/A".
                        """),

                Part.fromBytes(imageData, "image/jpeg")
        );

        Schema certificateSchema =
                Schema.builder()
                        .type(Type.Known.OBJECT)
                        .properties(
                                Map.of(
                                        "certificateName",
                                        Schema.builder()
                                                .type(Type.Known.STRING)
                                                .build(),

                                        "issuingOrganization",
                                        Schema.builder()
                                                .type(Type.Known.STRING)
                                                .build(),

                                        "courseDuration",
                                        Schema.builder()
                                                .type(Type.Known.STRING)
                                                .build(),

                                        "issueDate",
                                        Schema.builder()
                                                .type(Type.Known.STRING)
                                                .build()
                                )
                        )
                        .required(List.of(
                                "certificateName",
                                "issuingOrganization",
                                "courseDuration",
                                "issueDate"
                        ))
                        .build();

        GenerateContentConfig config =
                GenerateContentConfig.builder()
                        .responseMimeType("application/json")
                        .responseSchema(certificateSchema)
                        .build();

        Client client = new Client();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3.7-flash",
                        content,
                        config
                );

        return response.text();
    }
}