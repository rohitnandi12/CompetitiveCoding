package com.codesignal;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://garmadon.notion.site/Uber-scaled-solutions-Generative-AI-ddc40726ddaa4a578e20db7e16285ffc
 * https://garmadon.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F0be809f4-04c2-4ed1-a319-440600827738%2F1b06d981-78d9-4d00-b985-f81983df350a%2FScreenshot_from_2024-06-19_15-15-12.png?table=block&id=737099da-172a-4e57-bf57-6546e4704318&spaceId=0be809f4-04c2-4ed1-a319-440600827738&width=2000&userId=&cache=v2
 * https://garmadon.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F0be809f4-04c2-4ed1-a319-440600827738%2F2a5f5528-043c-4c75-ada1-44d3c4302eaa%2FScreenshot_from_2024-06-19_15-15-26.png?table=block&id=2c9fbf10-734c-420a-aaaa-2a688f723fc1&spaceId=0be809f4-04c2-4ed1-a319-440600827738&width=2000&userId=&cache=v2
 * https://garmadon.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F0be809f4-04c2-4ed1-a319-440600827738%2Faa57306b-16a4-4fcd-8db5-c63ed4f71426%2FScreenshot_from_2024-06-19_15-43-20.png?table=block&id=ceae50c2-38fa-4b0e-9c7e-bf07d7c84c24&spaceId=0be809f4-04c2-4ed1-a319-440600827738&width=2000&userId=&cache=v2
 */
public class LastBusDeparture {

    public static void main(String[] args) {
        List<String> departures = List.of("23:10", "12:30", "14:00", "19:55");
        System.out.println(solution(departures, "12:30"));
        System.out.println(solution(departures, "14:30"));
        System.out.println(solution(departures, "14:00"));
        System.out.println(solution(departures, "23:59"));
        System.out.println(solution(departures, "00:00"));
    }

    public static Integer solution(List<String> departures, String currentTime) {
        List<Integer> departureInMinutes = departures.stream().map(LastBusDeparture::hhmmToMinutes).collect(Collectors.toList());
        Integer currentTimeInMinutes = hhmmToMinutes(currentTime);
        Collections.sort(departureInMinutes);
        int lastDepartureIndex = nearestElementIndex(departureInMinutes, currentTimeInMinutes);
        return lastDepartureIndex == -1 ? -1 : currentTimeInMinutes - departureInMinutes.get(lastDepartureIndex);
    }

    public static int nearestElementIndex(List<Integer> elements, Integer element) {
        int start = 0;
        int end = elements.size() - 1;

        while (start <= end) {

            int m = start + (end - start) / 2;
            int middleEle = elements.get(m);
            if (element <= middleEle) {
                end = m - 1;
            } else {
                start = m + 1;
            }
        }

        return end;
    }

    public static int hhmmToMinutes(String hhmm) {
        String[] hhmm_split = hhmm.split(":");
        return Integer.parseInt(hhmm_split[0]) * 60 + Integer.parseInt(hhmm_split[1]);
    }
}
