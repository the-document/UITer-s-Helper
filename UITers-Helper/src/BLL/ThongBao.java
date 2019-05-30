/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

/**
 *
 * @author mirushi
 */

public class ThongBao {
    private String name;
    private String url;
    
    public ThongBao()
    {
        name = url = "";
    }
    
    public ThongBao(String _name, String _url)
    {
        name = _name;
        url = _url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
