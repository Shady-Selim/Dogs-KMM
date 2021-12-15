import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ContentVM
    @State var breedSpinnerText: String = "Select a dog breed"

    var style = -1
	var body: some View {
        VStack(alignment: .leading ){
            Text("Select a dog breed")
            getBreeds()
            drawDogs()
        }
	}
    
    private func getBreeds() -> AnyView {
        switch viewModel.breedsData {
            case .loading:
                return AnyView(Text("Loading Breeds...").multilineTextAlignment(.center))
            case .result(let breedsData):
                return AnyView( Menu{
                ForEach(breedsData, id: \.self) { data in
                    Button(data.name, action:{getDogs(breedName: data.name, id: data.id)})
                }
            } label: {
                Text(breedSpinnerText)
                Image(systemName: "arrowtriangle.down.fill")
           })
            case .error(let description):
                return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
    
    private func getDogs(breedName: String, id: Int32){
        breedSpinnerText = breedName
        viewModel.getDogs(breedId: id)
    }
    
    private func drawDogs() -> AnyView{
        switch viewModel.dogsData {
            case .loading:
                return AnyView(Text("Loading Dogs...").multilineTextAlignment(.center))
            case .result(let dogsData):
                return dogsGrid(dogsData: dogsData)
            case .error(let description):
                return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

private func dogsGrid(dogsData: [Dog]) -> AnyView{
    var items: [GridItem] {
      Array(repeating: .init(.flexible()), count: 2)
    }
    return AnyView(
        ScrollView(.vertical, showsIndicators: false) {
            LazyVGrid(columns: items, spacing: 10){
                ForEach(0..<dogsData.count) { i in
                    if #available(iOS 15.0, *) {
                        AsyncImage(url: URL(string: dogsData[i].url), transaction: .init(animation: .spring(response: 1.6))){ phase in
                            switch phase {
                            case .empty:
                                ProgressView()
                                    .progressViewStyle(.circular)
                            case .success(let image):
                                image
                                    .resizable()
                                    .aspectRatio(contentMode: .fill)
                            case .failure:
                                Text("Couldn't Load Image").fontWeight(.bold)
                            @unknown default:
                                Text("Couldn't Load Image").fontWeight(.bold)
                            }
                        }
                        .frame(width: 70, height: 100)
                    }
                }
            }
        }
    )
}


struct ContentView_Previews: PreviewProvider {

	static var previews: some View {
		ContentView(viewModel: .init(repo: DogsRepo()))
	}
}

extension ContentView {
    enum LoadableBreeds {
        case loading
        case result([Breed])
        case error(String)
    }
    enum LoadableDogs {
        case loading
        case result([Dog])
        case error(String)
    }
}

