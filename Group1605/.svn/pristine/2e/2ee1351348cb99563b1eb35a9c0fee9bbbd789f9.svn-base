package com.nightly.lovetravel.bean;

import java.util.List;

/**
 * Created by Nightly on 2016/10/22.
 */

public class Location {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"addressComponent":{"distance":"7","direction":"附近","street":"中关村大街","province":"北京市","adcode":"110108","street_number":"27号1101-08室","district":"海淀区","country_code":0,"city":"北京市","country":"中国"},"poiRegions":[],"ret_code":0,"location":{"lng":116.32298703398993,"lat":39.983424122651655},"formatted_address":"北京市海淀区中关村大街27号1101-08室","pois":[],"sematic_description":"北京远景国际公寓(中关村店)内0米","business":"中关村,人民大学,苏州街"}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * addressComponent : {"distance":"7","direction":"附近","street":"中关村大街","province":"北京市","adcode":"110108","street_number":"27号1101-08室","district":"海淀区","country_code":0,"city":"北京市","country":"中国"}
     * poiRegions : []
     * ret_code : 0
     * location : {"lng":116.32298703398993,"lat":39.983424122651655}
     * formatted_address : 北京市海淀区中关村大街27号1101-08室
     * pois : []
     * sematic_description : 北京远景国际公寓(中关村店)内0米
     * business : 中关村,人民大学,苏州街
     */

    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * distance : 7
         * direction : 附近
         * street : 中关村大街
         * province : 北京市
         * adcode : 110108
         * street_number : 27号1101-08室
         * district : 海淀区
         * country_code : 0
         * city : 北京市
         * country : 中国
         */

        private AddressComponentBean addressComponent;
        private int ret_code;
        /**
         * lng : 116.32298703398993
         * lat : 39.983424122651655
         */

        private LocationBean location;
        private String formatted_address;
        private String sematic_description;
        private String business;
        private List<?> poiRegions;
        private List<?> pois;

        public AddressComponentBean getAddressComponent() {
            return addressComponent;
        }

        public void setAddressComponent(AddressComponentBean addressComponent) {
            this.addressComponent = addressComponent;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public String getSematic_description() {
            return sematic_description;
        }

        public void setSematic_description(String sematic_description) {
            this.sematic_description = sematic_description;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public List<?> getPoiRegions() {
            return poiRegions;
        }

        public void setPoiRegions(List<?> poiRegions) {
            this.poiRegions = poiRegions;
        }

        public List<?> getPois() {
            return pois;
        }

        public void setPois(List<?> pois) {
            this.pois = pois;
        }

        public static class AddressComponentBean {
            private String distance;
            private String direction;
            private String street;
            private String province;
            private String adcode;
            private String street_number;
            private String district;
            private int country_code;
            private String city;
            private String country;

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getStreet_number() {
                return street_number;
            }

            public void setStreet_number(String street_number) {
                this.street_number = street_number;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public int getCountry_code() {
                return country_code;
            }

            public void setCountry_code(int country_code) {
                this.country_code = country_code;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }
        }

        public static class LocationBean {
            private double lng;
            private double lat;

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }
    }
}
