package com.avalon.qrspringserver.utils.DTO;

import com.avalon.qrspringserver.model.Menu;

import java.util.Date;
import java.util.List;

public class RestaurantDTO {
    private String id;
    private String name;
    private List<Menu> menus;
    private String url;
    private String qr;
    private String email;
    private Date createdAt;
    private Date updatedAt;
}
