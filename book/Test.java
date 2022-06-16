@Slf4j
public class MatchWriter {
    private static FileWriter fileWriter;

    public static synchronized void clear() {
        try {
            init(false);
            fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static synchronized void write(String content, boolean append) {
        try {
            init(append);
            fileWriter.write(content + "\r\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void init(boolean append) throws IOException {
        File file = new File("csvFileReader/data/Match.txt");
        if(fileWriter == null) {
            if(!file.exists()) {
                File parent = file.getParentFile();
                if(!parent.exists()) {
                    parent.mkdirs();
                }
                file.createNewFile();
            }
            fileWriter = new FileWriter(file, append);
        }
        try {
            fileWriter.write("");
        } catch (IOException e) {
            log.info("当前io已被关闭，重新初始化...");
            fileWriter = new FileWriter(file, append);
        }

    }

    public static void close() {
        if(fileWriter != null) {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
