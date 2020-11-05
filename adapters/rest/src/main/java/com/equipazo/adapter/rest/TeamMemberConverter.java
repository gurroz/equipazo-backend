package com.equipazo.adapter.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;

public class TeamMemberConverter implements Converter<String, TeamMemberDTO> {

    @Override
    @SneakyThrows
    public TeamMemberDTO convert(String source) {
        ObjectMapper objectMapper = new ObjectMapper();
        TeamMemberDTO teamMemberData = objectMapper.readValue(source, TeamMemberDTO.class);

        return teamMemberData;
    }
}
