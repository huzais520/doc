package com.core.bo;


import java.util.*;

/**
 * Created by scn7th on 2023/7/21.
 */
public class GithubClubUtil {
        /**
         * 用户名
         */
        private String username;

        /**
         * token
         */
        private String token;

        /**
         * 仓库名
         */
        private String depository = "doc";

        /**
         * 统一提交信息
         */
        private String message = "test";

        /**
         * 自定义域名
         */
        private String domain;

        private String GITHUBAPI = "https://api.github.com";

        /**
         * 文件推送到github
         * @param fileName  文件名
         * @param content json 数据格式 {"message":"test commit","content":"bXkgbmV3IGZpbGUgY29udGVudHM="}
         * @return
         */
        public HashMap<String, Object> contentFile(String fileName, String content){
            String url = GITHUBAPI + "/repos/"+username+"/"+depository+"/contents/"+fileName;
            HashMap<String, String> header = new HashMap<>();
            header.put("Authorization","token "+token);
            header.put("Accept","Accept: application/vnd.github+json");
            String base64 = Base64.getEncoder().encodeToString(content.getBytes());
            String json = "{\"message\":\""+message+"\",\"content\":\""+base64+"\",\"sha\": \"asdas\"}";
//            String base64 = Base64.getEncoder().encodeToString(json.getBytes());
            String res = HttpUtils.putJson(url, json, header);
            return filterResultForContentFile(res,fileName);
        }

        /**
         * 处理调用接口后返回的值
         * @param res
         * @param fileName
         * @return
         */
        private HashMap<String,Object> filterResultForContentFile(String res,String fileName){
            System.out.println(res + ";" + fileName);
            return null;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getDepository() {
            return depository;
        }

        public void setDepository(String depository) {
            this.depository = depository;
        }
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }
    public static void main(String[] args) {
        GithubClubUtil githubClubUtil = new GithubClubUtil();
        String res = HttpUtils.getString(githubClubUtil.GITHUBAPI + "/repos/"+githubClubUtil.getUsername()+"/"+githubClubUtil.getDepository()+"/contents/"+"book/git.txt");

        githubClubUtil.contentFile("book/git.txt", "asdasd");
//        String res = HttpUtils.getString(githubClubUtil.GITHUBAPI + "/repos/"+githubClubUtil.getUsername()+"/"+githubClubUtil.getDepository()+"/contents/"+"book/git.txt");
//        System.out.println(res);


    }
}
