package com.io.request.model.types;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Optional;

public enum RequestStatus {

    OPEN("OPEN", "1"),
    TRIAGE("TRIAGE", "2"),
    IN_PROGRESS("IN_PROGRESS", "3"),
    CLOSED("CLOSED", "4");

    private String statusName;
    private String  statusId;

    public String getStatusName() {
        return statusName;
    }

    public String getStatusId() {
        return statusId;
    }

    RequestStatus(String statusName, String statusId) {
        this.statusName = statusName;
        this.statusId = statusId;
    }

    public static String retrieveStatusNameForStatusId(String statusId) {
        Optional<RequestStatus> currentRequestStatus = Arrays.stream(RequestStatus.values())
                .filter(requestStatus -> requestStatus.getStatusId().equals(statusId))
                .findFirst();

        if(currentRequestStatus.isPresent())
            return currentRequestStatus.get().getStatusName();

        return "DEFAULT";
    }
}
