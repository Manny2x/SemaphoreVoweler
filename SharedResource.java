package semaphores.project1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class SharedResource {
    static int vowelCount = 0;
    static StringBuffer vowels = new StringBuffer();
    public static String sharedFile;

    static {
        StringBuffer stringBuffer = new StringBuffer();


        try(SeekableByteChannel seekableByteChannel =
                    Files.newByteChannel(
                            Path.of("C:" +
                                    "\\Users\\" +
                                    "Emman" +
                                    "\\IdeaProjects\\" +
                                    "CouncurrencyUtilities" +
                                    "\\src\\" +
                                    "semaphores" +
                                    "\\project1\\" +
                                    "Information.txt")
                    )){
            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            int count;


            do {
                count = seekableByteChannel.read(byteBuffer);

                if(count != -1){
                    byteBuffer.rewind();

                    for(int i = 0; i < count; i++)
                        stringBuffer.append((char) byteBuffer.get());
                }
            } while(count != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sharedFile = String.valueOf(stringBuffer);
    }
}
