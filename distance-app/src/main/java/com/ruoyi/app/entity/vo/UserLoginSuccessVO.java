package com.ruoyi.app.entity.vo;

import com.ruoyi.app.entity.domain.DistanceUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginSuccessVO {
    private DistanceUser distanceUser;
    private String token;
}
