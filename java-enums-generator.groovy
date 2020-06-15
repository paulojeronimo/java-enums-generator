import groovy.json.JsonSlurper
import groovy.text.SimpleTemplateEngine as Engine
import static StaticFunctions.*

config = loadConfig()
slurper = config.slurper
package_name = slurper.enums.package_name
package_dir = package_name.replaceAll(/\./, '/')

def create_interface(o) {
  interface_name = o.get('implements_')
  println "Creating interface $package_name.$interface_name ..."
  // NOTE: remove the "def" keyword below an you will get an unexpected result
  def output_file = new File("$output_dir/$package_dir/${interface_name}.java")
  output_file.getParentFile().mkdirs()
  output_file.write(new Engine()
    .createTemplate(new File("./EnumInterface.java.template"))
    .make(
      [ package_name: package_name
      , interface_name: interface_name
      , java_fields: o.java_fields ])
    .toString())
}

slurper.enums.set.each {
  output_dir = 'build'
  parsed = new JsonSlurper().parse(new URL(it.getValue().url))
  enum_name = it.getKey()
  println "Creating enum $package_name.${enum_name} ..."
  output_file = new File("$output_dir/$package_dir/${enum_name}.java")
  output_file.getParentFile().mkdirs()
  has_interface(it.getValue()) && create_interface(it.getValue())
  output_file.write(new Engine()
    .createTemplate(new File("./Enum.java.template"))
    .make(
      [ package_name: package_name
      , enum_name: enum_name
      , java_fields: it.getValue().java_fields
      , items: parsed.collect(it.getValue().enum_item)
      , implements_: implements_(it.getValue()) ])
    .toString())
}
