import SwiftUI
import app

struct ContentView: View {

	let greet = Greeting().greeting()

    @State var fullForecast: [Forecast] = []

	var body: some View {
        List(self.fullForecast, id: \.name) { forecast in
            CardView(forecast: forecast)
        }
        .onAppear {
            getForecasts()
        }
	}

   func getForecasts() {
       WeatherRepository().getFullForecast { forecast, error in
           guard let forecast = forecast else {
               return
           }
           self.fullForecast = forecast.forecasts
       }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

struct CardView: View {
    let forecast: Forecast

    var body: some View {
        HStack {
            VStack {
                Text(forecast.name)
                    .font(.headline)
                    .accessibilityAddTraits(.isHeader)
                Text(forecast.shortForecast)
                    .font(.subheadline)
            }
//            AsyncImage(url: URL(string: forecast.icon))
            Text("\(forecast.temperature)\(forecast.temperatureUnit)")
                .font(.headline)
        }
        .padding()
    }
}
