package social.roo.utils;

import com.blade.kit.Hashids;
import com.blade.kit.StringKit;
import com.vdurmont.emoji.EmojiParser;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.List;

/**
 * Roo工具类
 *
 * @author biezhi
 * @date 2017/8/2
 */
public class RooUtils {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz1234567890";

    private static final Hashids hashids = new Hashids("blade-roo", 12, ALPHABET);

    public static String genTid() {
        return hashids.encode(1002, System.currentTimeMillis());
    }

    public static Long decodeId(String hash) {
        return hashids.decode(hash)[1];
    }

    /**
     * markdown转换为html
     *
     * @param markdown
     * @return
     */
    public static String mdToHtml(String markdown) {
        if (StringKit.isBlank(markdown)) {
            return "";
        }

        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser          parser     = Parser.builder().extensions(extensions).build();
        Node            document   = parser.parse(markdown);
        HtmlRenderer    renderer   = HtmlRenderer.builder().extensions(extensions).build();
        String          content    = renderer.render(document);
        content = EmojiParser.parseToUnicode(content);
        return content;
    }

    /**
     * 提取html中的文字
     *
     * @param html
     * @return
     */
    public static String htmlToText(String html) {
        if (StringKit.isNotBlank(html)) {
            return html.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
        }
        return "";
    }

    /**
     * 计算帖子权重
     * <p>
     * 根据点赞数、收藏数、评论数、下沉数、创建时间计算
     *
     * @param likes     点赞数：权重占比1
     * @param favorites 收藏数：权重占比2
     * @param comments  评论数：权重占比2
     * @param gains     增益数：权重占比-1
     * @param created   创建时间，越早权重越低
     * @return
     */
    public static double calcWeight(int likes, int favorites, int comments, int gains, long created) {
        long score = Math.max(likes - 1, 1) + favorites * 2 + comments * 2 - gains;
        // 投票方向
        int sign = (score == 0) ? 0 : (score > 0 ? 1 : -1);
        // 帖子争议度
        double order = Math.log10(Math.max(Math.abs(score), 1));
        // 1501748867是项目创建时间
        double seconds = created - 1501748867;
        return Double.parseDouble(String.format("%.2f", order + sign * seconds / 45000));
    }

}
