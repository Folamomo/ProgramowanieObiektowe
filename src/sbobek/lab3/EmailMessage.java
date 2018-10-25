package sbobek.lab3;

import java.util.LinkedList;

public class EmailMessage {

    private String from;
    private LinkedList<String> to;
    private String subject;
    private String content;
    private String mimeType;
    private LinkedList<String> cc;
    private LinkedList<String> bcc;

    private EmailMessage(String from, LinkedList<String> to, String subject, String content,
                         String mimeType, LinkedList<String> cc, LinkedList<String> bcc) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.mimeType = mimeType;
        this.cc = cc;
        this.bcc = bcc;
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
            to.add(to_);
            return this;
        }


        public EmailMessage.Builder addSubject (String subject_){
            subject = subject_;
            return this;
        }


        public EmailMessage.Builder addContent (String content_){
            content=content_;
            return this;
        }

        public EmailMessage.Builder addMimeType (String mimeType_){
            mimeType=mimeType_;
            return this;
        }

        public EmailMessage.Builder addCC (String cc_){
            cc.add(cc_);
            return this;
        }

        public EmailMessage.Builder addBCC (String bcc_){
            bcc.add(bcc_);
            return this;
        }
        public EmailMessage build(){
            return new EmailMessage(from, to, subject, content, mimeType, cc, bcc);
        }
    }
}
