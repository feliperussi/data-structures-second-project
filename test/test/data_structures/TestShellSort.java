package test.data_structures;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.ShellSort;

@SuppressWarnings("all")
public class TestShellSort {
    private Integer[] sortedInt;
    private Integer[] integers;
    private String[] sortedStr;
    private String[] strings;

    @Before
    public void setUp1() {
        int[] pIntegers = { 9, 4, 7, 2, 4, 3, 1, 7 };
        int[] pSorted = { 1, 2, 3, 4, 4, 7, 7, 9 };

        integers = new Integer[pIntegers.length];

        for (int i = 0; i < pIntegers.length; i++) {
            integers[i] = Integer.valueOf(pIntegers[i]);
        }

        sortedInt = new Integer[pSorted.length];

        for (int i = 0; i < pSorted.length; i++) {
            sortedInt[i] = Integer.valueOf(pSorted[i]);
        }
    }

    @Before
    public void setUp2() {
        // Arreglo por defecto
        int[] pIntegers = { 995, 389, 612, 741, 347, 484, 422, 323, 237, 186, 510, 915, 997, 466, 953, 71, 5, 938, 560,
                372, 333, 996, 821, 178, 779, 792, 978, 642, 39, 455, 54, 151, 246, 792, 942, 638, 577, 851, 653, 798,
                98, 905, 374, 322, 571, 210, 394, 58, 621, 267, 77, 948, 672, 692, 982, 837, 148, 435, 164, 42, 574,
                101, 688, 338, 189, 570, 241, 503, 111, 963, 757, 143, 728, 317, 51, 357, 476, 413, 611, 653, 761, 62,
                894, 269, 337, 966, 907, 88, 788, 666, 969, 190, 217, 828, 573, 227, 4, 475, 172, 389, 406, 358, 616,
                869, 699, 219, 820, 918, 699, 51, 221, 33, 721, 697, 48, 374, 104, 436, 104, 133, 983, 514, 624, 720,
                364, 570, 551, 609, 71, 241, 800, 976, 757, 51, 657, 248, 192, 271, 806, 430, 100, 560, 516, 234, 73,
                84, 422, 335, 411, 508, 493, 507, 256, 653, 359, 178, 602, 600, 706, 466, 282, 945, 543, 211, 970, 607,
                761, 72, 1000, 955, 934, 248, 288, 217, 106, 113, 418, 274, 534, 324, 335, 182, 494, 100, 583, 425, 967,
                607, 762, 986, 253, 557, 379, 277, 884, 881, 835, 318, 250, 114 };
        int[] pSorted = { 4, 5, 33, 39, 42, 48, 51, 51, 51, 54, 58, 62, 71, 71, 72, 73, 77, 84, 88, 98, 100, 100, 101,
                104, 104, 106, 111, 113, 114, 133, 143, 148, 151, 164, 172, 178, 178, 182, 186, 189, 190, 192, 210, 211,
                217, 217, 219, 221, 227, 234, 237, 241, 241, 246, 248, 248, 250, 253, 256, 267, 269, 271, 274, 277, 282,
                288, 317, 318, 322, 323, 324, 333, 335, 335, 337, 338, 347, 357, 358, 359, 364, 372, 374, 374, 379, 389,
                389, 394, 406, 411, 413, 418, 422, 422, 425, 430, 435, 436, 455, 466, 466, 475, 476, 484, 493, 494, 503,
                507, 508, 510, 514, 516, 534, 543, 551, 557, 560, 560, 570, 570, 571, 573, 574, 577, 583, 600, 602, 607,
                607, 609, 611, 612, 616, 621, 624, 638, 642, 653, 653, 653, 657, 666, 672, 688, 692, 697, 699, 699, 706,
                720, 721, 728, 741, 757, 757, 761, 761, 762, 779, 788, 792, 792, 798, 800, 806, 820, 821, 828, 835, 837,
                851, 869, 881, 884, 894, 905, 907, 915, 918, 934, 938, 942, 945, 948, 953, 955, 963, 966, 967, 969, 970,
                976, 978, 982, 983, 986, 995, 996, 997, 1000 };

        integers = new Integer[pIntegers.length];

        for (int i = 0; i < pIntegers.length; i++) {
            integers[i] = Integer.valueOf(pIntegers[i]);
        }

        sortedInt = new Integer[pSorted.length];

        for (int i = 0; i < pSorted.length; i++) {
            sortedInt[i] = Integer.valueOf(pSorted[i]);
        }
    }

    @Before
    public void setUp3() {
        // Arreglo por defecto
        strings = new String[] { "Luisa", "Mario", "Felipe" };
        sortedStr = new String[] { "Felipe", "Luisa", "Mario" };
    }

    @Before
    public void setUp4() {
        // Arreglo por defecto
        strings = new String[] { "zzereetq", "dezfmkhu", "aigoawti", "jzshwmot", "mjmvhded", "felemoax", "qinzoqvh",
                "oexiapai", "elsqeook", "kyofeumb", "suumuewb", "wtrhbese", "uddpgeyq", "defdfsyt", "zguefech",
                "oahrzump", "djxhuyua", "teqwtfmh", "tawcibaz", "dqfyjint", "mhpdbwzo", "mujflopi", "sadvduac",
                "wflrmkpv", "xbmckmft", "tdvrhbxb", "qalbnjzu", "kjartpbm", "avxejjal", "pkgjepcj", "ryyvrtsa",
                "rcaeyeii", "dcyyxdmk", "tkpziili", "zwhgcylu", "ryabxckb", "ratrmxai", "qtkfcroi", "jqqudkku",
                "suevjfcy", "xruyfjdd", "enbospgj", "wooxdpth", "kcqqyjso", "zcvfycnv", "digzvgiv", "ozxvbowx",
                "bsokaire", "kstpdtgx", "vochmtor", "uqjnwwep", "jngooalx", "mgugiupb", "fssptgxy", "fpiidivm",
                "hembixfa", "kjumntvk", "zzvybywq", "kdqqbcqg", "czhjbzde", "caevpdga", "unwzophe", "abhhzssm",
                "qxykevhf", "owbsncnn", "hmltmcwz", "tgfqxkgp", "nycphzle", "zryrjcew", "ozngzxlk", "heaajuqd",
                "oywudeto", "nagaifgc", "sytuewfx", "uahhgkaw", "fucaayqj", "kljtjaqq", "ksuckhhl", "ylfyisup",
                "uunkzzwj", "zvwucjov", "rglvfgui", "qomnnimt", "xerkljzr", "wjgditmp", "faurakjn", "jtypspcv",
                "yjhhiokw", "typoluap", "afqoonzf", "kznxvnrs", "jibouhrt", "ovzoyxno", "wmpbxvqc", "pzxveqfa",
                "qmdydghl", "gslzjvqi", "zuwtklqk", "vvilqxyj", "rkhadkru", "ybnnzigk", "jdygkkam", "krpgxels",
                "juocsoqm", "twhtxorw", "ngacmimn", "jsxjrtpi", "fbfqadsw", "zajojxzi", "jgcprdrx", "bxmznkdn",
                "fxcuwykw", "zicciwzc", "uplvjkyy", "zbyefohm", "rlmltbmq", "keqlyutb", "nrcyanwg", "rkfxkhbe",
                "mvpcnvap", "ktlwpmmq", "arnbcwxc", "ivybrnlq", "dbmgnwit", "qgdagang", "wmxormmx", "rzjglcrh",
                "fhdhlnme", "mvpqfrkj", "rklkqqlg", "ymyodrey", "tmurezcr", "unbhslof", "tobkabxb", "ggeadyuj",
                "vdwhscdf", "yckwgzpj", "ahgtqstn", "rkoxqeob", "jeceswat", "slvgwfmj", "gozhdaoz", "cifiwsma",
                "zmcjltbt", "hfvhojqs", "pkwumltr", "quprntaz", "otwfqncp", "zefrrdan", "xfzcmzuv", "lmosefaq",
                "aldxvwsv", "wgkdceaz", "yjtoxtjh", "twbstrko", "trvokjss", "lsnesadf", "eytuzdzs", "xbfayowq",
                "ouuhepsw", "mrrbcipg", "ltfnqivl", "wtcbrnnd", "bvlfzxcp", "loeqycoz", "edflkpiz", "ewwairxx",
                "thwflgxb", "npypfzsn", "mpwzeuum", "rnmntayo", "eipnfktn", "htigovsg", "zvarbyvj", "bpcjhjhh",
                "ytalqizu", "skdqqlxy", "mcpozxvc", "keifbhqa", "cpuqozvr", "khszgbop", "mguhyvbk", "jgvmlkbf",
                "jmibiuaf", "xobshybh", "vkyiloci", "wgivseud", "uoyzdsnp", "lngrjpkr", "lwoytkpz", "vlkmoztt",
                "ngitcmkw", "bfpxznbl", "yfykdfeu", "gidzvdqz", "sxozrvnh", "gmkyqnym", "egeezorr", "cstjjphe",
                "ognbpkhs" };
        sortedStr = new String[] { "abhhzssm", "afqoonzf", "ahgtqstn", "aigoawti", "aldxvwsv", "arnbcwxc", "avxejjal",
                "bfpxznbl", "bpcjhjhh", "bsokaire", "bvlfzxcp", "bxmznkdn", "caevpdga", "cifiwsma", "cpuqozvr",
                "cstjjphe", "czhjbzde", "dbmgnwit", "dcyyxdmk", "defdfsyt", "dezfmkhu", "digzvgiv", "djxhuyua",
                "dqfyjint", "edflkpiz", "egeezorr", "eipnfktn", "elsqeook", "enbospgj", "ewwairxx", "eytuzdzs",
                "faurakjn", "fbfqadsw", "felemoax", "fhdhlnme", "fpiidivm", "fssptgxy", "fucaayqj", "fxcuwykw",
                "ggeadyuj", "gidzvdqz", "gmkyqnym", "gozhdaoz", "gslzjvqi", "heaajuqd", "hembixfa", "hfvhojqs",
                "hmltmcwz", "htigovsg", "ivybrnlq", "jdygkkam", "jeceswat", "jgcprdrx", "jgvmlkbf", "jibouhrt",
                "jmibiuaf", "jngooalx", "jqqudkku", "jsxjrtpi", "jtypspcv", "juocsoqm", "jzshwmot", "kcqqyjso",
                "kdqqbcqg", "keifbhqa", "keqlyutb", "khszgbop", "kjartpbm", "kjumntvk", "kljtjaqq", "krpgxels",
                "kstpdtgx", "ksuckhhl", "ktlwpmmq", "kyofeumb", "kznxvnrs", "lmosefaq", "lngrjpkr", "loeqycoz",
                "lsnesadf", "ltfnqivl", "lwoytkpz", "mcpozxvc", "mgugiupb", "mguhyvbk", "mhpdbwzo", "mjmvhded",
                "mpwzeuum", "mrrbcipg", "mujflopi", "mvpcnvap", "mvpqfrkj", "nagaifgc", "ngacmimn", "ngitcmkw",
                "npypfzsn", "nrcyanwg", "nycphzle", "oahrzump", "oexiapai", "ognbpkhs", "otwfqncp", "ouuhepsw",
                "ovzoyxno", "owbsncnn", "oywudeto", "ozngzxlk", "ozxvbowx", "pkgjepcj", "pkwumltr", "pzxveqfa",
                "qalbnjzu", "qgdagang", "qinzoqvh", "qmdydghl", "qomnnimt", "qtkfcroi", "quprntaz", "qxykevhf",
                "ratrmxai", "rcaeyeii", "rglvfgui", "rkfxkhbe", "rkhadkru", "rklkqqlg", "rkoxqeob", "rlmltbmq",
                "rnmntayo", "ryabxckb", "ryyvrtsa", "rzjglcrh", "sadvduac", "skdqqlxy", "slvgwfmj", "suevjfcy",
                "suumuewb", "sxozrvnh", "sytuewfx", "tawcibaz", "tdvrhbxb", "teqwtfmh", "tgfqxkgp", "thwflgxb",
                "tkpziili", "tmurezcr", "tobkabxb", "trvokjss", "twbstrko", "twhtxorw", "typoluap", "uahhgkaw",
                "uddpgeyq", "unbhslof", "unwzophe", "uoyzdsnp", "uplvjkyy", "uqjnwwep", "uunkzzwj", "vdwhscdf",
                "vkyiloci", "vlkmoztt", "vochmtor", "vvilqxyj", "wflrmkpv", "wgivseud", "wgkdceaz", "wjgditmp",
                "wmpbxvqc", "wmxormmx", "wooxdpth", "wtcbrnnd", "wtrhbese", "xbfayowq", "xbmckmft", "xerkljzr",
                "xfzcmzuv", "xobshybh", "xruyfjdd", "ybnnzigk", "yckwgzpj", "yfykdfeu", "yjhhiokw", "yjtoxtjh",
                "ylfyisup", "ymyodrey", "ytalqizu", "zajojxzi", "zbyefohm", "zcvfycnv", "zefrrdan", "zguefech",
                "zicciwzc", "zmcjltbt", "zryrjcew", "zuwtklqk", "zvarbyvj", "zvwucjov", "zwhgcylu", "zzereetq",
                "zzvybywq" };
    }

    @Test
    public void testIntegers() {
        setUp1();
        ShellSort.sort(integers);
        assertEquals(integers, sortedInt);

        setUp2();
        ShellSort.sort(integers);
        assertEquals(integers, sortedInt);
    }

    @Test
    public void testStrings() {
        setUp3();
        ShellSort.sort(strings);
        assertEquals(strings, sortedStr);

        setUp4();
        ShellSort.sort(strings);
        assertEquals(strings, sortedStr);
    }
}
