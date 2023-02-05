package com.ouiuo.timetable.model;

import jakarta.persistence.*;
import jakarta.transaction.NotSupportedException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.math3.util.Pair;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name = "groups")
@Table(name = "groups", uniqueConstraints = {@UniqueConstraint(columnNames = {"group_name", "group_number", "is_practicum"})})
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "group_name", length = 50)
    private String groupName;

    @Column(name = "group_number")
    private Integer groupNumber;

    @Column(length = 100)
    private String url;

    @Column(name = "is_practicum")
    private boolean isPracticum;

    //todo this is doing two things. have to refactor
    public Group(String groupNameNumber, String url) throws NotSupportedException {
        Pair<String, Integer> pair = parseNameNumber(groupNameNumber);
        this.groupName = pair.getFirst();
        this.groupNumber = pair.getSecond();
        this.url = url;
        this.isPracticum = url.contains("pract");
    }

    public static Pair<String, Integer> parseNameNumber(String groupNameNumber) throws NotSupportedException {
        String[] groupNameOrNumber = groupNameNumber.split("-");
        String name = "";
        int number = 0;
        try {
            try {
                number = Integer.parseInt(groupNameOrNumber[0].replaceAll("\\s", ""));
                name = groupNameOrNumber[1];
            } catch (NumberFormatException e) {
                number = Integer.parseInt(groupNameOrNumber[1].replaceAll("\\s", ""));
                name = groupNameOrNumber[0];
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new NotSupportedException(String.format("Not supported group name. Expected format 12..89-aZ actual name %s", groupNameNumber));
        }

        return new Pair<>(name, number);
    }

}
