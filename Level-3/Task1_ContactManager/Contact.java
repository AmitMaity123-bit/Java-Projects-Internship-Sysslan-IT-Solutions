public class Contact {

    private int    id;
    private String name;
    private String phone;
    private String email;
    private String group;   // e.g. "Friend", "Work", "Family"

    public Contact(int id, String name, String phone,
                   String email, String group) {
        this.id    = id;
        this.name  = name.trim();
        this.phone = phone.trim();
        this.email = email.trim();
        this.group = group.trim().isEmpty() ? "General" : group.trim();
    }

    public int    getId()    { return id;    }
    public String getName()  { return name;  }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getGroup() { return group; }

    public void setName(String name)   { this.name  = name.trim();  }
    public void setPhone(String phone) { this.phone = phone.trim(); }
    public void setEmail(String email) { this.email = email.trim(); }
    public void setGroup(String group) { this.group = group.trim(); }

    public String toCSV() {
        return id + "," + name + "," + phone + "," + email + "," + group;
    }

    public static Contact fromCSV(String line) {
        String[] p = line.split(",", 5);
        if (p.length < 5) return null;
        return new Contact(Integer.parseInt(p[0].trim()),
                           p[1], p[2], p[3], p[4]);
    }

    @Override
    public String toString() {
        return String.format("  [%3d] %-22s %-15s %-28s (%s)",
                id, name, phone, email, group);
    }
}