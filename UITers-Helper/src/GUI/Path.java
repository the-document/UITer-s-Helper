/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.List;

/**
 *
 * @author Admin
 */
public class Path {

    public String path;
    public List<String> form;

    public Path() {
    }

    public void UpdatePath() {
        path = "";
        for (int i = 0; i < form.size() - 1; i++) {
            path += form.get(i);
            path += " | ";
        }
        path += form.get(form.size() - 1);

    }
    
    public int Search(String form_name) {
        for (int i = 0; i < form.size(); i++) {
            if (form.get(i).compareTo(form_name) == 0) {
                return i;
            }
        }
        return -1;
    }
    public void AddtoForm(String form_name) {
        if (Search(form_name) != -1) {
            form.remove(Search(form_name));
        }
        else {
            
            form.add(form.size(), form_name);
        }
    }
}
