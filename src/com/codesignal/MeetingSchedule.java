package com.codesignal;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * https://garmadon.notion.site/Uber-scaled-solutions-Generative-AI-ddc40726ddaa4a578e20db7e16285ffc
 * https://garmadon.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F0be809f4-04c2-4ed1-a319-440600827738%2F5e866484-b5b9-46f3-b932-85c6c58cdbce%2FScreenshot_from_2024-06-19_16-14-16.png?table=block&id=1ef13928-b873-4a92-a589-88ee04db9e32&spaceId=0be809f4-04c2-4ed1-a319-440600827738&width=1360&userId=&cache=v2
 * https://garmadon.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F0be809f4-04c2-4ed1-a319-440600827738%2F2ded30b7-278f-4e67-9f59-ba0f8250db0a%2FScreenshot_from_2024-06-19_16-14-30.png?table=block&id=5a70829a-ff62-4d7f-8dd5-d2fae04e48e4&spaceId=0be809f4-04c2-4ed1-a319-440600827738&width=1360&userId=&cache=v2
 * https://garmadon.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F0be809f4-04c2-4ed1-a319-440600827738%2Fcd14abd7-e6ba-4ec2-8eec-b2bd820891c8%2FScreenshot_from_2024-06-19_16-14-39.png?table=block&id=716c916f-ab67-4431-ae14-5fe26344dec1&spaceId=0be809f4-04c2-4ed1-a319-440600827738&width=1360&userId=&cache=v2
 *https://garmadon.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F0be809f4-04c2-4ed1-a319-440600827738%2F1ed39933-f28a-4494-b367-7f1ddd8c0e45%2FScreenshot_from_2024-06-19_16-14-46.png?table=block&id=7cb51217-8c32-4d7f-b104-926396d1ab79&spaceId=0be809f4-04c2-4ed1-a319-440600827738&width=1360&userId=&cache=v2
 */
public class MeetingSchedule {

    public static void main(String[] args) {
        List<List<List<Integer>>> meetings = List.of(
                List.of(
                        List.of(60, 150), List.of(180, 240)
                ),
                List.of(
                        List.of(0, 210),List.of(360, 420),List.of(3, 4)
                ),
                List.of(
                        List.of(0, 210), List.of(360, 420)
                )
        );
        int ans = solution(meetings, 120);
        System.out.println(ans);


        meetings = List.of(
                List.of(
                        List.of(480, 510)
                ),
                List.of(
                        List.of(240, 330)
                ),
                List.of(
                        List.of(375, 400)
                )
        );
        ans = solution(meetings, 180);
        System.out.println(ans);

        meetings = List.of(
                List.of(
                        List.of(0, 1439)
                ),
                List.of(
                        List.of(0, 1439)
                ),
                List.of(
                        List.of(0, 390), List.of(480, 510)
                )
        );
        ans = solution(meetings, 90);
        System.out.println(ans);
    }

    public static Integer solution(List<List<List<Integer>>> meetings, int length) {

        List<List<Integer>> bookedSlots = meetings.stream()
                .flatMap(List::stream) // Stream<List<List<Integer>>> -> Stream<List<Integer>>
                .collect(Collectors.toList()); // Collect to List<List<Integer>>

        bookedSlots.sort((f,s) -> !f.get(0).equals(s.get(0)) ? f.get(0).compareTo(s.get(0)) : f.get(1).compareTo(s.get(1)));

        int lastMeetingEndTime = 0;
        for(int i=0; i<bookedSlots.size(); i++) {

            int nextMeetingStartTime = i == bookedSlots.size() - 1 ? 24*60 : bookedSlots.get(i).get(0);

            if(nextMeetingStartTime - lastMeetingEndTime >= length) {
                return lastMeetingEndTime;
            }

            lastMeetingEndTime = Math.max(lastMeetingEndTime, bookedSlots.get(i).get(1));
        }
//        System.out.println(bookedSlots);
        return -1;
    }
}
