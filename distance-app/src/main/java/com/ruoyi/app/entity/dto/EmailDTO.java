package com.ruoyi.app.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {

    private String email;

    private String phone;


    private String event;

    public void setPropertiesFromMap(Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            switch (entry.getKey()) {
                case "email":
                    this.email = entry.getValue();
                    break;
                case "phone":
                    this.phone = entry.getValue();
                    break;
                case "event":
                    this.event = entry.getValue();
                    break;
                default:
                    // Handle unknown keys if necessary
                    break;
            }
        }
    }

}
