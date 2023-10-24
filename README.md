# Sunshine
Weather Forecast App
O projeto é um app para conferir a previsão para os próximos 14 dias e detalhes de cada horário do dia
Ele possibilita ajustar a localização da previsão e a unidade de temperatura de acordo com o gosto de cada um.

Ao rodar o projeto, temos a página principal que contém a previsão dos próximos 14 dias, sendo que o dia atual em destaque fica na parte superior e aparece a localização que vem essa previsão.
Cada dia contém temperatura max e min, descrição e imagem do tempo.
A localização da previsão está como Brasil por default até ser alterada na tela Settings.
A unidade do tempo está como celsius por default podendo ser alterada no settings também.

Ao clicar em qualquer um dos dias, o app vai navegar para uma tela com os detalhes de previsão do dia selecionado, além das informações que já apareciam antes, é possível visualizar a velocidade do vento, humidade, chance de chuva e previsão para cada um dos horários daquele dia.

Na tela inicial tem um icone na nav bar que ao clicar o app vai navegar para a tela de settings onde é possível alterar a localização da previsão podendo ser nome do país, cidade e latitude e longitude.
Também é possível escolher se deseja visulizar as temperaturas em celsius ou fahrenheit.

O app suporta dark mode.

A API utilizada para os dados de previsão é https://www.weatherapi.com/docs/
Esse é o endpoint default: https://api.weatherapi.com/v1/forecast.json?key=601200df17174c75aec193618232110&q=Brasil&lang=pt&days=14

Para acessar a API utilizei o Retrofit, Okttp e Serializable
