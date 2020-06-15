import static StaticFunctions.*

config = loadConfig()
println "Testing \"${config.file.name}\" filled with ${config.isSample ? 'a content sample' : 'your content'} ..."

print "Configuration should define a map of properties ... "
assert config.slurper.enums instanceof Map
println "ok!"

keyset = [ 'package_name', 'set' ] as Set
print "Configuration should have a keySet equals to ${keyset} ... "
assert config.slurper.enums.keySet() == keyset
println "ok!"

print "Configuration should have at least one set ... "
assert config.slurper.enums.set.keySet().size() >= 1
println "ok!"
