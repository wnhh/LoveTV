package com.yztc.lovetv.bean;

import java.util.List;

/**
 * Created by My on 2016/12/17.
 */

public class FirstPagerBean {

    private List<AppFocusBean> app_focus;


    public List<AppFocusBean> getApp_focus() {
        return app_focus;
    }

    public void setApp_focus(List<AppFocusBean> app_focus) {
        this.app_focus = app_focus;
    }

    public static class AppFocusBean {
        /**
         * id : 1654
         * title : 全民星盛典开赛
         * thumb : http://image.quanmin.tv/844e4a76ee4c2bb5a3f1de1c77e49de4jpg
         * link : http://m.quanmin.tv/static/v2/m/boot/special/activity/act.html
         * create_at : 2016-12-03 19:47:58
         * status : 1
         * fk :
         * subtitle :
         * content :
         * ext : {"type":"ad"}
         * slot_id : 113
         * priority : 1
         * link_object : {"title":"TDT2016腾讯斗地主锦标赛","nick":"腾讯棋牌盛典","avatar":"http://image.quanmin.tv/avatar/be09ec1ca09aff8da18dc6fff1786542?imageView2/2/w/300/","category_id":"42","category_name":"棋牌游戏","weight":"0","follow":"7","level":"0","intro":"TPT2016 第三届腾讯扑克锦标赛 将于14：:30开始","create_at":"2016-12-18 12:37:37","play_at":"2016-12-18 12:37:37","slug":"","status":"1","thumb":"http://image.quanmin.tv/b53794c0380f13d56cb073a88cf39fe5jpg","uid":"9898078","category_slug":"qipai","view":"17631","video_quality":"","recommend_image":"","app_shuffling_image":"","default_image":"","grade":"","announcement":"","love_cover":"","beauty_cover":"","like":"0","screen":0,"start_time":"2016-12-18 14:38:22","video":"","year_type":0,"hidden":false}
         */

        private int id;
        private String title;
        private String thumb;
        private String link;
        private String create_at;
        private int status;
        private String fk;
        private String subtitle;
        private String content;
        private ExtBean ext;
        private int slot_id;
        private int priority;
        private LinkObjectBean link_object;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getFk() {
            return fk;
        }

        public void setFk(String fk) {
            this.fk = fk;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public ExtBean getExt() {
            return ext;
        }

        public void setExt(ExtBean ext) {
            this.ext = ext;
        }

        public int getSlot_id() {
            return slot_id;
        }

        public void setSlot_id(int slot_id) {
            this.slot_id = slot_id;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public LinkObjectBean getLink_object() {
            return link_object;
        }

        public void setLink_object(LinkObjectBean link_object) {
            this.link_object = link_object;
        }

        public static class ExtBean {
            /**
             * type : ad
             */

            private String type;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class LinkObjectBean {
            /**
             * title : TDT2016腾讯斗地主锦标赛
             * nick : 腾讯棋牌盛典
             * avatar : http://image.quanmin.tv/avatar/be09ec1ca09aff8da18dc6fff1786542?imageView2/2/w/300/
             * category_id : 42
             * category_name : 棋牌游戏
             * weight : 0
             * follow : 7
             * level : 0
             * intro : TPT2016 第三届腾讯扑克锦标赛 将于14：:30开始
             * create_at : 2016-12-18 12:37:37
             * play_at : 2016-12-18 12:37:37
             * slug :
             * status : 1
             * thumb : http://image.quanmin.tv/b53794c0380f13d56cb073a88cf39fe5jpg
             * uid : 9898078
             * category_slug : qipai
             * view : 17631
             * video_quality :
             * recommend_image :
             * app_shuffling_image :
             * default_image :
             * grade :
             * announcement :
             * love_cover :
             * beauty_cover :
             * like : 0
             * screen : 0
             * start_time : 2016-12-18 14:38:22
             * video :
             * year_type : 0
             * hidden : false
             */

            private String title;
            private String nick;
            private String avatar;
            private String category_id;
            private String category_name;
            private String weight;
            private String follow;
            private String level;
            private String intro;
            private String create_at;
            private String play_at;
            private String slug;
            private String status;
            private String thumb;
            private String uid;
            private String category_slug;
            private String view;
            private String video_quality;
            private String recommend_image;
            private String app_shuffling_image;
            private String default_image;
            private String grade;
            private String announcement;
            private String love_cover;
            private String beauty_cover;
            private String like;
            private int screen;
            private String start_time;
            private String video;
            private int year_type;
            private boolean hidden;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getFollow() {
                return follow;
            }

            public void setFollow(String follow) {
                this.follow = follow;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public String getPlay_at() {
                return play_at;
            }

            public void setPlay_at(String play_at) {
                this.play_at = play_at;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getCategory_slug() {
                return category_slug;
            }

            public void setCategory_slug(String category_slug) {
                this.category_slug = category_slug;
            }

            public String getView() {
                return view;
            }

            public void setView(String view) {
                this.view = view;
            }

            public String getVideo_quality() {
                return video_quality;
            }

            public void setVideo_quality(String video_quality) {
                this.video_quality = video_quality;
            }

            public String getRecommend_image() {
                return recommend_image;
            }

            public void setRecommend_image(String recommend_image) {
                this.recommend_image = recommend_image;
            }

            public String getApp_shuffling_image() {
                return app_shuffling_image;
            }

            public void setApp_shuffling_image(String app_shuffling_image) {
                this.app_shuffling_image = app_shuffling_image;
            }

            public String getDefault_image() {
                return default_image;
            }

            public void setDefault_image(String default_image) {
                this.default_image = default_image;
            }

            public String getGrade() {
                return grade;
            }

            public void setGrade(String grade) {
                this.grade = grade;
            }

            public String getAnnouncement() {
                return announcement;
            }

            public void setAnnouncement(String announcement) {
                this.announcement = announcement;
            }

            public String getLove_cover() {
                return love_cover;
            }

            public void setLove_cover(String love_cover) {
                this.love_cover = love_cover;
            }

            public String getBeauty_cover() {
                return beauty_cover;
            }

            public void setBeauty_cover(String beauty_cover) {
                this.beauty_cover = beauty_cover;
            }

            public String getLike() {
                return like;
            }

            public void setLike(String like) {
                this.like = like;
            }

            public int getScreen() {
                return screen;
            }

            public void setScreen(int screen) {
                this.screen = screen;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public int getYear_type() {
                return year_type;
            }

            public void setYear_type(int year_type) {
                this.year_type = year_type;
            }

            public boolean isHidden() {
                return hidden;
            }

            public void setHidden(boolean hidden) {
                this.hidden = hidden;
            }
        }
    }
}
