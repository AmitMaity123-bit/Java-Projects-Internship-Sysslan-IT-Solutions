/**
 * Student.java
 * Encapsulates a student's academic record.
 * Computes average and letter grade automatically.
 */
public class Student {

    private int      id;
    private String   name;
    private String   rollNumber;
    private double[] marks;       // marks for each subject
    private String[] subjects;

    // ── Constructor ────────────────────────────────────────────
    public Student(int id, String name, String rollNumber,
                   String[] subjects, double[] marks) {
        this.id         = id;
        this.name       = name.trim();
        this.rollNumber = rollNumber.trim().toUpperCase();
        this.subjects   = subjects;
        this.marks      = marks;
    }

    // ── Computed fields ────────────────────────────────────────
    public double getAverage() {
        if (marks.length == 0) return 0;
        double sum = 0;
        for (double m : marks) sum += m;
        return sum / marks.length;
    }

    public String getGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A+";
        if (avg >= 80) return "A";
        if (avg >= 70) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        if (avg >= 40) return "E";
        return "F";
    }

    public String getStatus() {
        return getAverage() >= 40 ? "PASS" : "FAIL";
    }

    // ── Getters ────────────────────────────────────────────────
    public int      getId()         { return id; }
    public String   getName()       { return name; }
    public String   getRollNumber() { return rollNumber; }
    public String[] getSubjects()   { return subjects; }
    public double[] getMarks()      { return marks; }

    // ── Setters ────────────────────────────────────────────────
    public void setName(String name)         { this.name = name.trim(); }
    public void setRollNumber(String roll)   { this.rollNumber = roll.trim().toUpperCase(); }
    public void setMarks(double[] marks)     { this.marks = marks; }
    public void setSubjects(String[] subj)   { this.subjects = subj; }

    // ── CSV (pipe-delimited for commas-in-names safety) ────────
    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("|")
          .append(name).append("|")
          .append(rollNumber).append("|");

        for (int i = 0; i < subjects.length; i++)
            sb.append(subjects[i]).append(i < subjects.length - 1 ? "," : "");
        sb.append("|");
        for (int i = 0; i < marks.length; i++)
            sb.append(marks[i]).append(i < marks.length - 1 ? "," : "");
        return sb.toString();
    }

    public static Student fromCSV(String line) {
        String[] p = line.split("\\|", 5);
        if (p.length < 5) return null;
        int    id    = Integer.parseInt(p[0].trim());
        String name  = p[1];
        String roll  = p[2];
        String[] subj  = p[3].split(",");
        String[] markS = p[4].split(",");
        double[] marks = new double[markS.length];
        for (int i = 0; i < markS.length; i++)
            marks[i] = Double.parseDouble(markS[i].trim());
        return new Student(id, name, roll, subj, marks);
    }

    // ── Summary line ──────────────────────────────────────────
    @Override
    public String toString() {
        return String.format("  [%3d] %-10s %-22s Avg:%6.2f  Grade:%-3s  %s",
                id, rollNumber, name, getAverage(), getGrade(), getStatus());
    }
}