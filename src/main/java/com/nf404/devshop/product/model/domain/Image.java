package com.nf404.devshop.product.model.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Image {

    private int thumbnailPath;
    private String uuidFilename;
    private String originFilename;
}
