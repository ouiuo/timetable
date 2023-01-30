package com.ouiuo.timetable.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.NotSupportedException;
import lombok.Data;
import org.apache.commons.math3.util.Pair;

@Data
@Entity(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String groupName;

    private Long groupNumber;

    private String url;

    private boolean isPracticum;
    public Group(String groupNameNumber, String url) throws NotSupportedException {
        Pair<String, Long> pair = parseNameNumber(groupNameNumber);
        this.groupName = pair.getFirst();
        this.groupNumber = pair.getSecond();
        this.url = url;
        this.isPracticum = url.contains("pract");
    }

    public Group() {

    }

    public static Pair<String, Long> parseNameNumber(String groupNameNumber) throws NotSupportedException {
        String[] groupNameOrNumber = groupNameNumber.split("-");
        String name = "";
        Long number = 0L;
        try {
            try {
                number = Long.parseLong(groupNameOrNumber[0].replaceAll("\\s", ""));
                name = groupNameOrNumber[1];
            } catch (NumberFormatException e) {
                number = Long.parseLong(groupNameOrNumber[1].replaceAll("\\s", ""));
                name = groupNameOrNumber[0];
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new NotSupportedException(String.format("Not supported group name. Expected format 12..89-aZ actual name %s", groupNameNumber));
        }

        return new Pair<>(name, number);
    }

}
