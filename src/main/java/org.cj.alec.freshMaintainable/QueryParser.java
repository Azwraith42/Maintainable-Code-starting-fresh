package org.cj.alec.freshMaintainable;

class QueryParser {

    static String lookupFirstInstance(String lookupTarget, String queryString) {
        String[] queryParts = queryString.split("&");
        for (String queryPart : queryParts) {
            String matching = lookupMatching(lookupTarget, queryPart);
            if(matching != null) return matching;
        }
        return null;
    }

    private static String lookupMatching(String lookupTarget, String queryPart) {
        String[] queryParts = queryPart.split("=");
        String name = queryParts[0];
        String value = queryParts[1];

        if(name.equals(lookupTarget)) {
            return value;
        }else{
            return null;
        }
    }

}
