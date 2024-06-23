package com.nf404.devshop.product.domain;

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
