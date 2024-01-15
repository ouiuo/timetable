package com.ouiuo.timetable.model;

import jakarta.persistence.*;
import jakarta.transaction.NotSupportedException;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.math3.util.Pair;

import java.util.UUID;

@NoArgsConstructor
@Entity(name = "groups")
@Getter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;

        Group group = (Group) o;

        if (isPracticum != group.isPracticum) return false;
        if (groupName != null ? !groupName.equals(group.groupName) : group.groupName != null) return false;
        if (groupNumber != null ? !groupNumber.equals(group.groupNumber) : group.groupNumber != null) return false;
        return url != null ? url.equals(group.url) : group.url == null;
    }

    @Override
    public int hashCode() {
        int result = groupName != null ? groupName.hashCode() : 0;
        result = 31 * result + (groupNumber != null ? groupNumber.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (isPracticum ? 1 : 0);
        return result;
    }
}
