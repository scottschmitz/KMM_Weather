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
            observeForecasts()
        }
	}

   func observeForecasts() {
//       let viewModel = ViewModelInjector().forecastViewModel()
//       viewModel.forecastStateLiveData
//           .addObserver { state in
//               print("State go here: \(String(describing: state))")
//           }
//       viewModel.refreshForecast()

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
        HStack(spacing: .zero) {
            VStack(alignment: .leading) {
                Text(forecast.name)
                    .font(.headline)
                    .accessibilityAddTraits(.isHeader)
                Text(forecast.shortForecast)
                    .font(.subheadline)
            }
            Spacer(minLength: 16)
            AsyncImage(url: URL(string: forecast.icon)) { image in
                image.resizable()
            } placeholder: {
                ProgressView()
            }
            .frame(width: 50, height: 50)
            Text("\(forecast.temperature)\(forecast.temperatureUnit)")
                .font(.headline)
        }
    }
}
