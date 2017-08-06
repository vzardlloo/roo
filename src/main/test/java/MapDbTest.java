import social.roo.utils.ArrayUtils;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.Arrays;
import java.util.Map;

/**
 * @author biezhi
 * @date 2017/8/2
 */
public class MapDbTest {

    public static void main(String[] args) {

        DB db = DBMaker.fileDB("roo.db").fileMmapEnable().make();

        Map<String, long[]> map = db.treeMap("follow")
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.LONG_ARRAY)
                .counterEnable()
                .createOrOpen();

        map.keySet();

        map.clear();

        map.put("biezhi", new long[]{1});

        long count = map.get("biezhi").length;
        System.out.println("粉丝数：" + count);
        System.out.println(Arrays.toString(map.get("biezhi")));

        map.put("biezhi", ArrayUtils.append(map.get("biezhi"), 22));

        count = map.get("biezhi").length;
        System.out.println("粉丝数：" + count);
        System.out.println(Arrays.toString(map.get("biezhi")));

        long[] values = ArrayUtils.remove(map.get("biezhi"), 22);
        map.put("biezhi", values);

        count = map.get("biezhi").length;
        System.out.println("粉丝数：" + count);
        System.out.println(Arrays.toString(map.get("biezhi")));

        db.close();
    }

}
