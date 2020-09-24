package com.equipazo.adapter.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;

public class TeamDataConverter implements Converter<String, TeamDataDTO> {

    @Override
    @SneakyThrows
    public TeamDataDTO convert(String source) {
        ObjectMapper objectMapper = new ObjectMapper();
        TeamDataDTO teamData = objectMapper.readValue(source, TeamDataDTO.class);

        return teamData;
    }
}
