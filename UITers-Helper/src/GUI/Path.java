package GUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Path {

    public String path;
    public List<String> form;

    public Path() {
        path = "";
        this.form = new ArrayList<String>();
    }

    public String UpdatePath(String current_form) {
        if (form.isEmpty()) { path = ""; return "";}
        path = "";
        for (int i = 0; i < form.size() - 1; i++) {
            path += form.get(i);
            path += " | ";
        }
        path += form.get(form.size() - 1) + " | [" + current_form + "]";
        return path;
    }
    
    public int Search(String form_name) {
        if (form.isEmpty() == true) {return -1;}
        for (int i = 0; i < form.size(); i++) {
            if (form.get(i).compareTo(form_name) == 0) {
                return i;
            }
        }
        return -1;
    }
    public void push(String form_name) {
        if (Search(form_name) != -1) {
            form.remove(Search(form_name));
            form.add(form.size(), form_name);
        }
        else {
            
            form.add(form.size(), form_name);
        }
    }
    public String pop() {
        String temp = form.get(form.size() - 1);
        form.remove(form.size()-1);
        return temp;
    }
    public static void main(String args[]) {
        Scanner scn = new Scanner(System.in);
        Path p = new Path();
        String a = scn.nextLine();
        int n = Integer.parseInt(a);
        for (int i = 0; i < n; i++) {
            String temp = scn.nextLine();
            if (temp.compareTo("1") == 0) {
                 String tmp = scn.nextLine();
                 p.push(tmp);
            }
            else {
                p.pop();
            }
            //p.UpdatePath();
            System.out.println(p.path);
        }
    }
}
