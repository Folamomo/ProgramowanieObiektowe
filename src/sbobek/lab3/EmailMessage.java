package sbobek.lab3;

import java.util.LinkedList;

public class EmailMessage {


    protected EmailMessage(Builder builder) {
        //TODO body
    }

    public static Builder builder() {
        return new EmailMessage.Builder();
    }

    static public class Builder{
        private String from;
        private LinkedList<String> to;
        private String subject;
        private String content;
        private String mimeType;
        private LinkedList<String> cc;
        private LinkedList<String> bcc;

        public EmailMessage.Builder addFrom (String from_){
            //TODO spr poprawnosc
            from = from_;
            return this;
        }

        public EmailMessage.Builder addTo (String to_){
            //TODO spr poprawnosc
            to.add(to_);
            return this;
        }


        public EmailMessage.Builder addSubject (String subject_){
            //TODO spr poprawnosc
            subject = subject_;
            return this;
        }


        public EmailMessage.Builder addContent (String content_){
            //TODO spr poprawnosc
            content=content_;
            return this;
        }


        public EmailMessage.Builder addCC (String cc_){
            //TODO spr poprawnosc
            cc.add(cc_);
            return this;
        }

        public EmailMessage.Builder addBCC (String bcc_){
            //TODO spr poprawnosc
            bcc.add(bcc_);
            return this;
        }
        public EmailMessage build(){
            return new EmailMessage(this);
        }
    }
}
