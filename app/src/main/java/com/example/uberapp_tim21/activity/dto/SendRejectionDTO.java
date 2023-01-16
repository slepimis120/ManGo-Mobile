package com.example.uberapp_tim21.activity.dto;

public class SendRejectionDTO {

        private String reason;

        public SendRejectionDTO(String reason){
            this.reason = reason;
        }

        public SendRejectionDTO(){}

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }


}
