#!/bin/bash

echo Preparing to test: deleting all meetings
echo ===============================
curl -X DELETE http://localhost:5000/meetings
echo
echo

echo Create meeting with one invitee
echo ===============================
curl -H "Content-Type: application/json" --data @postMeetingWithOneInvitee.json http://localhost:5000/meetings
echo
echo

echo Make invitee accept the meeting
echo ===============================
# This is by doing a PUT to /meetings/:meetingId/people/:id/attendance
curl -X PUT -H "Content-Type: application/json" --data @putInviteeAccepts.json http://localhost:5000/meetings/1/people/alex/attendance
echo
echo

echo Check status of meeting: it should be completed
echo ===============================================
curl http://localhost:5000/meetings/1
echo
echo
