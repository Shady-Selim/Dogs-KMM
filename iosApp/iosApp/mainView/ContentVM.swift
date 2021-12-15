//
//  ContentVM.swift
//  iosApp
//
//  Created by Shady on 14/12/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class ContentVM : ObservableObject {
    @Published var breedsData = ContentView.LoadableBreeds.loading
    @Published var dogsData = ContentView.LoadableDogs.loading

    private let repo: DogsRepo
    
    init(repo: DogsRepo){
        self.repo = repo
        self.getBreeds()
       // self.getDogs(breedId: 0)

    }
    
    func getBreeds() {
        self.breedsData = .loading
        repo.getBreeds(completionHandler:{ breedsData, error in
            if let breedsData = breedsData {
                self.breedsData = .result(breedsData)
            } else {
                self.breedsData = .error(error?.localizedDescription ?? "error")
            }
        })
    }
    
    func getDogs(breedId: Int32) {
        self.dogsData = .loading
        repo.getDogs(breedId: breedId, completionHandler:{ dogsData, error in
            if let dogsData = dogsData {
                self.dogsData = .result(dogsData)
            } else {
                self.dogsData = .error(error?.localizedDescription ?? "error")
            }
        })
    }
}

