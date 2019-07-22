package Exam;

import org.testng.annotations.Test;

import java.util.*;

public class Students {

    private StudentCard vasia = new StudentCard();
    private StudentCard petia = new StudentCard();
    private StudentCard enokentiy = new StudentCard();

    @Test
    public void students() {
        vasia.family = "Pupkin";                //без гетеров и сетеров и конструкторов
        petia.family = "Ivanov";
        enokentiy.family = "Sidorov";
        vasia.group = 1;
        petia.group = 2;
        enokentiy.group = 1;
        vasia.mark.mathGrade = 5;
        vasia.mark.biologyGrade = 4;
        vasia.mark.chemistGrade = 5;
        vasia.mark.engGrade = 3;
        vasia.mark.literatureGrade = 3;
        petia.mark.mathGrade = 5;
        petia.mark.biologyGrade = 3;
        petia.mark.chemistGrade = 5;
        petia.mark.engGrade = 5;
        petia.mark.literatureGrade = 5;
        petia.mark.mathGrade = 4;
        enokentiy.mark.biologyGrade = 3;
        enokentiy.mark.chemistGrade = 2;
        enokentiy.mark.engGrade = 3;
        enokentiy.mark.literatureGrade = 2;
        enokentiy.mark.mathGrade = 3;
        vasia.year = 1976;
        petia.year = 1975;
        enokentiy.year = 1972;

        List<StudentCard> studentsList = new ArrayList<>();
        studentsList.add(vasia);
        studentsList.add(petia);
        studentsList.add(enokentiy);
        studentsList.forEach(e -> e.averangeGrade = (double) (e.mark.mathGrade + e.mark.literatureGrade + e.mark.engGrade + e.mark.chemistGrade + e.mark.biologyGrade) / 5);

        studentsList.forEach(System.out::println);                      //До сортировки
        System.out.println();

        studentsList.sort(Comparator.comparing(o -> o.family));
        studentsList.sort(Comparator.comparing(o -> o.group));
        studentsList.forEach(System.out::println);                      //Сортировка по группе
        System.out.println();

        Map<Integer, Double> groupGrade = new LinkedHashMap<>();
        int groupNum = studentsList.get(0).group;
        int gradeNumber = 0;
        int summGrade = 0;
        for (StudentCard student : studentsList) {
            if (student.group != groupNum) {
                groupGrade.put(groupNum, ((double) summGrade / (double) gradeNumber));
                groupNum = student.group;
                summGrade = 0;
                gradeNumber = 0;
                summGrade += (student.mark.mathGrade + student.mark.literatureGrade + student.mark.engGrade + student.mark.chemistGrade + student.mark.biologyGrade);
                gradeNumber += 5;
            } else {
                summGrade += (student.mark.mathGrade + student.mark.literatureGrade + student.mark.engGrade + student.mark.chemistGrade + student.mark.biologyGrade);
                gradeNumber += 5;
            }
        }
        groupGrade.put(groupNum, ((double) summGrade / (double) gradeNumber));
        groupGrade.forEach((key, value) -> System.out.println("Группа: " + key + "  Средняя оценка по группе: " + value));       //Средняя оценка по группам
        System.out.println();

        studentsList.sort(Comparator.comparing(o -> o.year));
        System.out.println("Самый младший студент: " + studentsList.get(0).family);
        System.out.println("Самый старший студент: " + studentsList.get(studentsList.size() - 1).family);        //Самый младший и самый старший студенты
        System.out.println();

        Map<String, Double> studentsGrade = new LinkedHashMap<>();
        for (StudentCard student : studentsList) {
            studentsGrade.put(student.family, student.averangeGrade);
        }
        studentsGrade.forEach((key, value) -> System.out.println("Студент: " + key + "  Средняя оценка: " + value));
        System.out.println();

        studentsList.sort(Comparator.comparing(o -> o.group));
        groupNum = studentsList.get(0).group;
        String studName = studentsList.get(0).family;
        double maxGrade = 0;
        for (StudentCard student : studentsList) {
            if (student.group != groupNum) {
                System.out.println("Максимальный результат по группе: " + groupNum + " у студента: " + studName + " балы: " + maxGrade);
                groupNum = student.group;
                studName = student.family;
                maxGrade = student.averangeGrade;
            } else {
                if (student.averangeGrade > maxGrade) {
                    maxGrade = student.averangeGrade;
                    studName = student.family;
                }
            }
        }
        System.out.println("Максимальный результат по группе: " + groupNum + " у студента: " + studName + " балы: " + maxGrade);        //Максимальный результат по группе
    }

    public class StudentCard {
        public String family;
        public String name;
        public String surName;
        public int year;
        public int curse;
        public int group;
        public Marks mark = new Marks();
        public double averangeGrade;

        @Override
        public String toString() {
            return "StudentCard{" +
                    "family='" + family + '\'' +
                    ", name='" + name + '\'' +
                    ", surName='" + surName + '\'' +
                    ", year=" + year +
                    ", curse=" + curse +
                    ", group=" + group +
                    ", mark=" + mark +
                    '}';
        }

        public class Marks {
            public int mathGrade;
            public int engGrade;
            public int biologyGrade;
            public int chemistGrade;
            public int literatureGrade;

            @Override
            public String toString() {
                return "Marks{" +
                        "mathGrade=" + mathGrade +
                        ", engGrade=" + engGrade +
                        ", biologyGrade=" + biologyGrade +
                        ", chemistGrade=" + chemistGrade +
                        ", literatureGrade=" + literatureGrade +
                        '}';
            }
        }
    }
}
