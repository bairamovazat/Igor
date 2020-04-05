package com.apeiron.igor.model.memory.map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RectangleMap {
    public Integer width = 5;
    public Integer height = 5;
}
