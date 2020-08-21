
public class Validation {

    //Filtering Section
    public static boolean filter(Staff s, String temp){
        if(temp ==null|| temp.isEmpty())
            return true;
        //Comparison
        String lowerCaseFilter = temp.toLowerCase();
        if(s.getId().contains(lowerCaseFilter))
            return true;
        else if(s.getName().toLowerCase().contains(lowerCaseFilter))
            return true;
        else if(s.getDesignation().toLowerCase().contains(lowerCaseFilter))
            return true;
        else if(s.getSex().toLowerCase().contains(lowerCaseFilter))
            return true;
        else return Integer.toString(s.getSalary()).contains(lowerCaseFilter);
    }

    public static boolean filter(Doctor d, String temp){
        if(temp==null||temp.isEmpty())
            return true;
        //Comparison
        String lowerCaseFilter = temp.toLowerCase();
        if(d.getId().contains(lowerCaseFilter))
            return true;
        else if(d.getName().toLowerCase().contains(lowerCaseFilter))
            return true;
        else if(d.getSpecialist().toLowerCase().contains(lowerCaseFilter))
            return true;
        else if(d.getWorkTime().contains(lowerCaseFilter))
            return true;
        else if(d.getQualification().toLowerCase().contains(lowerCaseFilter))
            return true;
        else return Integer.toString(d.getRoom()).contains(lowerCaseFilter);
    }

    public static boolean filter(Patient p, String temp){
        if(temp==null||temp.isEmpty())
            return true;
        //Comparison
        String lowerCaseFilter = temp.toLowerCase();
        if(p.getId().contains(lowerCaseFilter))
            return true;
        else if(p.getName().toLowerCase().contains(lowerCaseFilter))
            return true;
        else if(p.getDisease().toLowerCase().contains(lowerCaseFilter))
            return true;
        else if(p.getSex().toLowerCase().contains(lowerCaseFilter))
            return true;
        else if(p.getAdmitStatus().toLowerCase().contains(lowerCaseFilter))
            return true;
        else return Integer.toString(p.getAge()).contains(lowerCaseFilter);
    }

    public static boolean filter(Medical m, String temp){
        if(temp==null||temp.isEmpty())
            return true;
        //Comparison
        String lowerCaseFilter = temp.toLowerCase();
        if(m.getName().toLowerCase().contains(lowerCaseFilter))
            return true;
        else if(m.getManufacturer().toLowerCase().contains(lowerCaseFilter))
            return true;
        else if(m.getExpiryDate().contains(lowerCaseFilter))
            return true;
        else if(Integer.toString(m.getCost()).contains(lowerCaseFilter))
            return true;
        else return Integer.toString(m.getCount()).contains(lowerCaseFilter);
    }

    public static boolean filter(Lab l, String temp){
        if(temp==null||temp.isEmpty())
            return true;
        //Comparison
        String lowerCaseFilter = temp.toLowerCase();
        if(l.getLab().toLowerCase().contains(lowerCaseFilter))
            return true;
        else return Integer.toString(l.getCost()).contains(lowerCaseFilter);
    }

    public static boolean filter(Facility f, String temp){
        if(temp==null||temp.isEmpty())
            return true;
        //Comparison
        String lowerCaseFilter = temp.toLowerCase();
        return f.getFacility().toLowerCase().contains(lowerCaseFilter);
    }

    //Validation Section
    public static boolean valid(Staff[] staff, String id, String name, String des,String sex, String Sal){
        if(!id.matches("\\d{3}"))
            return false;
        if(name.isEmpty())
            return false;
        if(des.isEmpty())
            return false;
        if(sex==null)
            return false;
        return Sal.matches("\\d+");
    }

    public static boolean valid(Doctor[] doctor, String id, String name, String spec, String WT, String Q, String room){
        if(!id.matches("\\d{3}"))
            return false;
        if(name.isEmpty())
            return false;
        if(spec.isEmpty())
            return false;
        if(!WT.matches("\\d{4}-\\d{4}"))
            return false;
        if(Q.isEmpty())
            return false;
        return room.matches("\\d{3}");
    }

    public static boolean valid(Patient[] patient,String id, String name, String d, String sex, String AS, String age ){
        if(!id.matches("\\d{3}"))
            return false;
        if(name.isEmpty())
            return false;
        if(d.isEmpty())
            return false;
        if(sex==null)
            return false;
        if(AS==null)
            return false;
        return age.matches("\\d+");
    }

    public static boolean valid(Medical[] medical,String name, String man, String exp, String cost, String count){
        if(name.isEmpty())
            return false;
        if(man.isEmpty())
            return false;
        if(exp.isEmpty())
            return false;
        if(!cost.matches("\\d+"))
            return false;
        return count.matches("\\d+");
    }

    public static boolean valid(Lab[] labo, String lab, String cost){
        if(lab.isEmpty())
            return false;
        return cost.matches("\\d+");
    }

    public static boolean valid(Facility[] facility, String fac){
        return !fac.isEmpty();
    }

}


