package com.nf404.devshop.product.dto.req;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ImageDto {

    private int thumbnailPath;
    private String uuidFilename;
    private String originFilename;
}
