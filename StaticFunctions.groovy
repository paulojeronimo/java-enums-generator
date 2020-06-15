class StaticFunctions {
  static final String CONFIG_DEFAULT = 'config.groovy'
  static final String CONFIG_SAMPLE = 'config.sample.groovy'

  static Map loadConfig() {
    def file = new File(CONFIG_DEFAULT)
    if (!file.exists())
      file = new File(CONFIG_SAMPLE)
    [ file: file
    , slurper: new ConfigSlurper().parse(file.toURL())
    , isSample: file.getName() == CONFIG_SAMPLE ]
  }

  static boolean has_interface(o) { o.containsKey('implements_') }

  static String implements_(o) { has_interface(o) ? "implements ${o.get('implements_')} " : "" }
}
