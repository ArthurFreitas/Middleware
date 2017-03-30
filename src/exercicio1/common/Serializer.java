package exercicio1.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {
	
	public Serializer(){
		
	}
	
	public byte[] serialize(Message message) throws IOException{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream outStream = new ObjectOutputStream(byteStream);

        outStream.writeObject(message);
        byte[] marshalledMessage = byteStream.toByteArray();
        
        return marshalledMessage;
	}
	
    public Message deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
        ObjectInputStream inputStream = new ObjectInputStream(byteStream);

        Message unmarshalledMessage = (Message) inputStream.readObject();

        return unmarshalledMessage;
    }
}
