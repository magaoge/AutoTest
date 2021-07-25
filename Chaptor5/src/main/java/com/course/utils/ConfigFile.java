package com.course.utils;


import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by mgg on 2021/7/23
 */

public class ConfigFile {
    //获取配置文件，并且声名其中字符为中文
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CANADA);
    //根据获取的配置文件对象，获取操作表的url
    public static String getUrl(InterfaceName interfaceName){
        String address = bundle.getString("test.url");
        String uri = "";

        if (interfaceName == InterfaceName.ADDUSER){
            uri = bundle.getString("addUser.uri");
        }
        if (interfaceName == InterfaceName.GETUSERINFO){
            uri = bundle.getString("getUserInfo.uri");
        }
        if (interfaceName == InterfaceName.GETUSERLIST){
            uri = bundle.getString("getUserList.uri");
        }
        if (interfaceName == InterfaceName.LOGIN){
            uri = bundle.getString("login.uri");
        }
        if (interfaceName == InterfaceName.UPDATEUSERINFO){
            uri = bundle.getString("updateUserInfo.uri");
        }

        String url = address+uri;
        return url;
    }

}
