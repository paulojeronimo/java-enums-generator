enums {
  package_name = 'com.example.JavaProject.enums'
  set {
    BRRegion {
      url = 'https://servicodados.ibge.gov.br/api/v1/localidades/regioes'
      enum_item = { "${it.sigla}(${it.id}, \"${it.nome}\")" }
      java_fields = [ id: 'int', name: 'String' ]
    }
    BRState {
      url = 'https://servicodados.ibge.gov.br/api/v1/localidades/estados'
      enum_item = { "${it.sigla}(${it.id}, \"${it.nome}\", BRRegion.${it.regiao.sigla})" }
      java_fields = [ id: 'int', name: 'String', region: 'BRRegion' ]
    }
  }
}
